Components
  Application system / server (user of the log system)
  Logger
    log(Level, message)
    debug(message)
    info(message)
    Level enum
    Debug
    Info
    …
  ConcurrentLinkedQueue
  Log consumer 
    Queue<Resource> archivedResources
  Appender
    Int getCurrentSize
    void append(msg)
    writeTo(Resource r)
  LogConfig
    maxBufferSize
    maxFileSize
    MaxArchivedBuffers
    MaxArchivedFiles

public record LogMessage (Timestamp t, LogLevel level, String msg) {};

@Singleton
public class Logger {
  boolean active;
  private LogConfig logConfig;
  private ConcurrentLinkedQueue<LogMessage> messageQueue;
  private LogConsumer logConsumer;
  private ArchiveManager archiveManager;
  private Lock lock;

  log(level, msg) {
    if (!active) {
      return;
    }

    if (lock.acquired()) {
      lock.wait();
    }

    messageQueue.append(new LogMessage(System.getCurrentTimeInMilli(), level, message));
  }

  shutdown() {
    active = false;
    logConsumer.shutdown();
    archiveManager.shutdown();
  }

  flush() {
    lock.acquire();
    while (messageQueue.size() > 0) {
      try {
        messageQueue.wait();
      } catch () {

      }
    }

    lock.release();
  }
}

public enum LogResourceType {
  FILE,
  MEM_BUFFER
}

public abstract Resource {
  LogResourceType type;
  LogResourceFactory<LogResourceType, clazz> factory;

  ofType (Type){
    factory.get(type).init();
  }
}

public class FileResource {
  init()
}

public class BufferResource {
  init()
}

public record LogConfig (LogResourceType type, int maxArchivedBuffers, int maxArchivedFiles) {}

@Singleton
public class LogConsumer implements Runnable {
  private ConcurrentLinkedQueue<LogMessage> messageQueue;
  private boolean closed;
  private LogConfig config;

  private LogAppender logAppender;
  private String logFormat;

  private Queue<Resource> archivedResources;

  LogConsumer (messageQueue, logAppender, logFormat) {
    closed = false;
    this.messageQueue = messageQueue;
    this.logAppender = logAppender;
    this.logFormat = logFormat;
  }

  public void shutdown() {
    closed = true;
  }

  public void run () {
    while (!closed || messageQueue.size()) {
      LogMessage msg = q.get();
      int currentSize = logAppender.getCurrentSize();
      String text = String.format(logFormat, msg.timestamp, msg.level, msg.text);
      
      if (currentSize + text.size() > logConfig.allowedSize) {
        Resource r = Resource.ofType(logConfig.getType());
        archivedResources.append(logAppender.getCurrentResource());
        archivedResources.notify();
        logAppender.writeTo(r);
      }

      logAppender.write(text);
      messageQueue.notify();
    }
  }
}

public class ArchiveManager implements Runnable {
  private Queue<Resource> archivedResources;
  private LogConfig logConfig;

  public void run () {
    while (!closed) {
      try {
        while (ar.size() > logConfig.maxArchivedSize()) {
          archivedResources.pop();
        }

        archivedResources.wait();
      } catch () {
  
      }
    }
  }
}

public class Appender {
  private BufferedOutputStream outStream;
  private Resource resource;

  public Appender(Resource r) {
    outStream = r.getOutStream();
  }

  public int getCurrentSize() {
    return outStream.getSize();
  }

  public void append(String msg) {
    outStream.write(msg);
  }

  pubic void writeTo(Resource r) {
    outStream = r.getBufferedOutputStream();
  }

  public Resource getCurrentResource () {
    return resource;
  }
}

