package ru.job4j.design.isp;

public class Example {
    //пример 1
    interface Vehicle {
        void sail();
        void ride();
        void fly();
    }

    class Airplane implements Vehicle {
        @Override
        public void sail() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void ride() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void fly() {
            System.out.println("Airplane is flying");
        }
    }

    //пример 2
    interface Guitar {
        void acousticSound();
        void distortionSound();
    }

    class AcousticGuitar implements Guitar {
        @Override
        public void acousticSound() {
            System.out.println("Make acoustic sound");
        }

        @Override
        public void distortionSound() {
            throw new UnsupportedOperationException();
        }
    }

    //пример 3
    interface Animal {
        void jump();
        void fly();
        void swim();
    }

    class Fish implements Animal {
        @Override
        public void jump() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void fly() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void swim() {
            System.out.println("Fish is swimming");
        }
    }
}
