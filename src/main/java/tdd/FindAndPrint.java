package tdd;

public class FindAndPrint {

    private int[] arr;

    public FindAndPrint(int[] arr) {
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int find(int value) {
        int rsl = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                rsl = arr[i];
                break;
            }
        }
        return rsl;
    }

    public void print(int value) {
        System.out.println(value);
    }
}
