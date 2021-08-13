package ru.job4j.design.lsp;

public class Examples {
    //пример 1 усилено предусловие
    class Bird {
        public void fly(int km)  {
            System.out.println("bird is flying. km: " + km);
        }
    }

    class Pinguin extends Bird {
        public void fly(int km) {
            if (km != 0) {
                throw new IllegalArgumentException("I Cant fly");
            }
        }
    }

    //пример 2 ослаблено постусловие
    class Worker {
        private int age;
        public Worker(int age) {
            this.age = age;
        }

        public void work() throws Exception {
            if (this.age >= 68) {
                throw new Exception("Too old for work.");
            }
            System.out.println("Im working.");
        }
    }

    class GasStationWorker extends Worker {
        public GasStationWorker(int age) {
            super(age);
        }
        public void work() {
            System.out.println("Im working.");
        }
    }

    //пример 3 нарушение инварианта
    static class Rectangle {
        protected double aSide;
        protected double bSide;

        public void setSides(double aSide, double bSide) {
            validate(aSide, bSide);
            this.aSide = aSide;
            this.bSide = bSide;
        }

        public double getaSide() {
            return aSide;
        }

        public double getbSide() {
            return bSide;
        }

        public Rectangle(double aSide, double bSide) throws IllegalArgumentException {
            validate(aSide, bSide);
            this.aSide = aSide;
            this.bSide = bSide;
        }
        public void validate(double aSide, double bSide) {
            if (aSide <= 0 || bSide <= 0) {
                throw new IllegalArgumentException("Sides should have positive values!");
            }
        }

        public void setSide(double aSide, double bSide) {
            validate(aSide, bSide);
            this.aSide = aSide;
            this.bSide = bSide;
        }

        @Override
        public String toString() {
            return "Rectangle{" + "aSide=" + aSide + ", bSide=" + bSide + '}';
        }
    }

    static class Square extends Rectangle {
        public Square(double aSide, double bSide) throws IllegalArgumentException {
            super(aSide, bSide);
        }

        @Override
        public void setSides(double aSide, double bSide) {
             this.aSide = aSide;
             this.bSide = bSide;
        }
    }
    public static void main(String[] args) {
        Rectangle rc = new Square(2.0, 3.0);
        rc.setSides(-1.0, 0.0);
    }

}
