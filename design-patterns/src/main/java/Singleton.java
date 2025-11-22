class Resource {
    private String name;

    private static Resource instance = null;

    public static Resource getResource() {
        if (instance == null) {
            instance = new Resource("imp resource");
        }

        return instance;
    }

    private Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

public class Singleton {
    public static void main(String[] args) {
        Resource r1 = Resource.getResource();
        System.out.println("Resource r1 " + r1.getName() + " " + r1.hashCode());

        Resource r2 = Resource.getResource();
        System.out.println("Resource r2 " + r2.getName() + " " + r2.hashCode());

        Resource r3 = Resource.getResource();
        System.out.println("Resource r3 " + r3.getName() + " " + r3.hashCode());

        Resource r4 = Resource.getResource();
        System.out.println("Resource r4 " + r4.getName() + " " + r4.hashCode());
    }
}
