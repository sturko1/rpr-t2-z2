package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double t1,t2;
    private boolean t1pripadnost, t2pripadnost;

    public Interval() {
        t1 = 0;
        t2 = 0;
        t1pripadnost = false;
        t2pripadnost = false;
    }

    public double getT1() {
        return t1;
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    public double getT2() {
        return t2;
    }

    public void setT2(double t2) {
        this.t2 = t2;
    }

    public boolean isT1pripadnost() {
        return t1pripadnost;
    }

    public void setT1pripadnost(boolean t1pripadnost) {
        this.t1pripadnost = t1pripadnost;
    }

    public boolean isT2pripadnost() {
        return t2pripadnost;
    }

    public void setT2pripadnost(boolean t2pripadnost) {
        this.t2pripadnost = t2pripadnost;
    }

    public Interval(double v, double v1, boolean b, boolean b1) {
        if (v > v1) throw new IllegalArgumentException();
        t1 = v;
        t2 = v1;
        t1pripadnost = b;
        t2pripadnost = b1;
    }

    public static Interval intersect(Interval i, Interval i2) {
        if (i.getT1() > i2.getT1()) {
            if (i.getT2() > i2.getT2()) {             //     x2     x1       y1   y2
                return new Interval(i.getT1(), i2.getT2(), i.isT1pripadnost(), i2.isT2pripadnost());
            }
            //else if (t2 < interval.getT2()) {
            else {
                return new Interval(i.getT1(), i.getT2(), i.isT1pripadnost(), i.isT2pripadnost());
            }
        }
        else if (i.getT1() < i2.getT1()) {                //     x1     x2       y1   y2
            if (i.getT2() > i2.getT2()) {
                return new Interval(i2.getT1(), i2.getT2(), i2.isT1pripadnost(), i2.isT2pripadnost());
            }
            else {
                return new Interval(i2.getT1(), i.getT2(), i2.isT1pripadnost(), i.isT2pripadnost());
            }
        }
        else {
            return new Interval(i.getT1(), i.getT2(), i.isT1pripadnost(), i.isT2pripadnost());
        }
    }

    public boolean isIn(double v) {
        if (v >= t1 && v < t2 && t1pripadnost) return true;
        else if (v  > t1 && v <= t2 && t2pripadnost) return true;  // svaki slucaj moras pokriti
        else return false;
    }

    public boolean isNull() {
        if (t1 == 0 && t2 == 0 && t1pripadnost == false && t2pripadnost == false) return true;
        return false;
    }

    @Override
    public String toString() {
        if (isNull()) return "()";
        if (t1pripadnost && t2pripadnost) return "[" + t1 + "," + t2 + "]";
        else if (t1pripadnost && !t2pripadnost) return "[" + t1 + "," + t2 + ")";
        else if (!t1pripadnost && t2pripadnost) return "(" + t1 + "," + t2 + "]";
        else  return "(" + t1 + "," + t2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        Interval i = (Interval)o;
        if (t1 == i.getT1() && t2 == i.getT2() && t1pripadnost == i.isT1pripadnost() && t2pripadnost == i.isT2pripadnost())
            return true;
        return false;
    }

    public Interval intersect(Interval interval) {
        if (t1 > interval.getT1()) {
            if (t2 > interval.getT2()) {             //     x2     x1       y1   y2
                return new Interval(t1, interval.getT2(), t1pripadnost, interval.isT2pripadnost());
            }
            //else if (t2 < interval.getT2()) {
            else {
                return new Interval(t1, t2, t1pripadnost, t2pripadnost);
            }
        }
        else if (t1 < interval.getT1()) {                //     x1     x2       y1   y2
            if (t2 > interval.getT2()) {
                return new Interval(interval.getT1(), interval.getT2(), interval.isT1pripadnost(), interval.isT2pripadnost());
            }
            else {
                return new Interval(interval.getT1(), t2, interval.isT1pripadnost(), t2pripadnost);
            }
        }
        else {
            return new Interval(t1, t2, t1pripadnost, t2pripadnost);
        }

    }
}
