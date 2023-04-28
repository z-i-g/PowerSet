public class PowerSet {
    Hashtable<String, String> hashTable;
    public PowerSet()
    {
        hashTable = new Hashtable<>(20000);
        // ваша реализация хранилища
    }

    public int size()
    {
        return hashTable.size();
        // количество элементов в множестве
    }


    public void put(String value)
    {
        if (!hashTable.contains(value))
            hashTable.put(value, value);
        // всегда срабатывает
    }

    public boolean get(String value)
    {
        return hashTable.contains(value);
        // возвращает true если value имеется в множестве,
        // иначе false
    }

    public boolean remove(String value)
    {
        return hashTable.remove(value, value);
        // возвращает true если value удалено
        // иначе false
    }

    public PowerSet intersection(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String str: set2.hashTable.values()) {
            if (this.hashTable.contains(str))
                result.put(str);
        }
        return result;
        // пересечение текущего множества и set2
    }

    public PowerSet union(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        if (set2.size() == 0 && this.size() == 0)
            return result;

        if (set2.size() == 0) {
            for (String str: this.hashTable.values()) {
                result.put(str);
            }
            return result;
        }

        if (this.size() == 0) {
            for (String str: set2.hashTable.values()) {
                result.put(str);
            }
            return result;
        }

        for (String str: set2.hashTable.values()) {
            result.put(str);
        }
        for (String str: this.hashTable.values()) {
            result.put(str);
        }
        return result;
        // объединение текущего множества и set2
    }

    public PowerSet difference(PowerSet set2)
    {
        PowerSet result = new PowerSet();
       if (set2.size() == 0 && this.size() == 0)
           return result;

       if (set2.size() == 0) {
            for (String str: this.hashTable.values()) {
                result.put(str);
            }
            return result;
        }

        if (this.size() == 0) {
            return result;
        }
        for (String str: this.hashTable.values()) {
            if (!set2.hashTable.contains(str))
                result.put(str);
        }
        return result;
        // разница текущего множества и set2
    }

    public boolean isSubset(PowerSet set2)
    {
        if (set2.size() == 0) {
            return false;
        }

        if (this.size() == 0) {
            return false;
        }

        if (set2.size() > this.size())
            return false;

        for (String str: set2.hashTable.values()) {
            if (!this.hashTable.contains(str))
                return false;
        }
        return true;
        // возвращает true, если set2 есть
        // подмножество текущего множества,
        // иначе false
    }
}