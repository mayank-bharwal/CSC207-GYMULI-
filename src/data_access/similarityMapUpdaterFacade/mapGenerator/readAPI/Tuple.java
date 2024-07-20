package data_access.similarityMapUpdaterFacade.mapGenerator.readAPI;

public class Tuple {
    private final String first;
    private final String second;

    public Tuple(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (first != tuple.first) return false;
        return second == tuple.second;
    }
}