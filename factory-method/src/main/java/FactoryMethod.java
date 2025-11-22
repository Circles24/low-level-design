enum Chutney {
    COCONUT,
    TOMATO,
    ONION,
    PEANUT
}

enum Batter {
    RICE,
    RICE_FERMENTED,
    RAGI,
    OATS,
    RICE_BEET,
    MOONG_DAL_RICE
}

enum Masala {
    RED_CHUTNEY,
    POTATO_CLASSIC,
    VEGETABLE,
    SCHECHUAN
}

enum Size {
    S,
    M,
    L,
    XL
}

class Dosa {
    private final Chutney chutney;
    private final Batter batter;
    private final Masala masala;
    private final int cookTime;
    private final Size size;

    Dosa(Chutney chutney, Batter batter, Masala masala, int cookTime, Size size) {
        this.chutney = chutney;
        this.batter = batter;
        this.masala = masala;
        this.cookTime = cookTime;
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + " chutney : " + chutney + " batter : " + batter + " masala : " + masala + " cookTime : " + cookTime + " size : " + size;
    }
}

class DosaFactory {
    public static Dosa getMasalaDosaClassic(Size size) {
        return new Dosa(Chutney.COCONUT, Batter.RICE_FERMENTED, Masala.POTATO_CLASSIC, 10, size);
    }

    public static Dosa getHealthyDosa(Size size) {
        return new Dosa(Chutney.PEANUT, Batter.RICE_BEET, Masala.VEGETABLE, 10, size);
    }

    public static Dosa getMysoreDosa(Size size) {
        return new Dosa(Chutney.PEANUT, Batter.RICE, Masala.POTATO_CLASSIC, 15, size);
    }

    public static Dosa getCustomDosa(Chutney chutney, Batter batter, Masala masala, int cookTime, Size size) {
        return new Dosa(chutney, batter, masala, cookTime, size);
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        Dosa classicDosa = DosaFactory.getMasalaDosaClassic(Size.S);
        Dosa healthyDosa = DosaFactory.getHealthyDosa(Size.M);
        Dosa mysoreDosa = DosaFactory.getMysoreDosa(Size.L);
        Dosa custom = DosaFactory.getCustomDosa(Chutney.TOMATO, Batter.OATS, Masala.RED_CHUTNEY, 7, Size.XL);

        System.out.println("Classic Dosa " + classicDosa);
        System.out.println("Healthy Dosa " + healthyDosa);
        System.out.println("Mysore Dosa " + mysoreDosa);
        System.out.println("Custom Dosa " + custom);
    }
}
