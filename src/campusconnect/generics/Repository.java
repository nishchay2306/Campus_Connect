package campusconnect.generics;

import java.util.function.Predicate;

public class Repository<T> {
    private CustomArrayList<T> items;
    public Repository()
    {
        this.items=new CustomArrayList<>();
    }
    public void add(T item)
    {
        items.add(item);
    }
    public CustomArrayList<T> filter(Predicate<T> condition)
    {
        CustomArrayList<T> result = new CustomArrayList<>();
        for(int i=0;i<items.size();i++){
            T item = items.get(i);
            if(condition.test(item)){
                result.add(item);
            }
        }
        return result;
    }
    public T findOne(Predicate<T> condition)
    {
        for(int i=0;i< items.size();i++){
            T item=items.get(i);
            if(condition.test(item)){
                return item;
            }
        }
        return null;
    }
    public CustomArrayList<T> getAll() {
        return items;
    }
}