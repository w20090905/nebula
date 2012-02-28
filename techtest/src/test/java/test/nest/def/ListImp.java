package test.nest.def;

import java.util.ArrayList;

import test.mvel.MvelFunc;

public class ListImp<T> implements List<T> {
    final ArrayList<T> list;

    public ListImp() {
        this.list = new ArrayList<T>();
    }

    public ListImp(ArrayList<T> list) {
        this.list = list;
    }

    // users[1]
    public T get(int index) {
        return list.get(index);
    }

    // users[1..6]
    public List<T> get(int from, int to) {
        ArrayList<T> newList = new ArrayList<T>();
        for (int i = from; i <= to; i++) {
            newList.add(list.get(i));
        }
        return new ListImp<T>(newList);
    }

    // users[1,3,6]
    public List<T> pick(int... indexes) {
        ArrayList<T> newList = new ArrayList<T>();
        for (int i = 0; i <= indexes.length; i++) {
            newList.add(list.get(indexes[i]));
        }
        return new ListImp<T>(newList);
    }

    // users[name=wangshilian]
    public List<T> pick(Method<Boolean, T> expr) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T t : this.list) {
            if (expr.call(t).value()) {
                newList.add(t);
            }
        }
        return new ListImp<T>(newList);
    }

    public List<T> pick(String expr) {
        Method<Boolean, T> f = new MvelFunc<Boolean, T>(expr);
        return this.pick(f);        
    }

    public List<T> plus(List<T> to) {
        ArrayList<T> newList = new ArrayList<T>();
        newList.addAll(list);
        newList.addAll(((ListImp<T>) to).list);
        return new ListImp<T>(newList);
    }

    public void foreach(Method<Result, T> expr) {
        for (T t : this.list) {
            expr.call(t);
        }
    }

    public Number sum(Method<Number, T> expr) {
        Number sum = expr.call(this.list.get(0));
        for (int i = 1; i < this.list.size(); i++) {
            sum = sum.plus(expr.call(this.list.get(i)));
        }
        return sum;
    }

    public Number avg(Method<Number, T> expr) {
        Number sum = expr.call(this.list.get(0));
        for (int i = 1; i < this.list.size(); i++) {
            sum = sum.plus(expr.call(this.list.get(i)));
        }
        return sum.avgBy(this.list.size());
    }

    public Number max(Method<Number, T> expr) {
        Number max = expr.call(this.list.get(0));
        for (int i = 1; i < this.list.size(); i++) {
            Number n = expr.call(this.list.get(i));
            max = max.compareTo(n) > 0 ? max : n;
        }
        return max;
    }

    public Number min(Method<Number, T> expr) {
        Number min = expr.call(this.list.get(0));
        for (int i = 1; i < this.list.size(); i++) {
            Number n = expr.call(this.list.get(i));
            min = min.compareTo(n) < 0 ? min : n;
        }
        return min;
    }

    public void foreach(String expr) {
        Method<Result, T> f = new MvelFunc<Result, T>(expr);
        this.foreach(f);
    }

    public Number sum(String expr) {
        Method<Number, T> f = new MvelFunc<Number, T>(expr);
        return this.sum(f);
    }

    public Number avg(String expr) {
        Method<Number, T> f = new MvelFunc<Number, T>(expr);
        return this.sum(f);
    }

    public Number max(String expr) {
        Method<Number, T> f = new MvelFunc<Number, T>(expr);
        return this.sum(f);
    }

    public Number min(String expr) {
        Method<Number, T> f = new MvelFunc<Number, T>(expr);
        return this.sum(f);
    }
}
