package cn.kotliner.java.box;

import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by benny on 5/28/17.
 */
public class BoxImpl2 implements BoxIf2 {
    public String get(int key) {
        return null;
    }

    public String get(Integer key) {
        return null;
    }

    public String get(Object key) {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean containsValue(Object value) {
        return false;
    }

    public String put(@Flow(target = "this.keys", targetIsContainer = true) Integer key, @Flow(target = "this.values", targetIsContainer = true) String value) {
        return null;
    }

    public String remove(Object key) {
        return null;
    }

    public void putAll(@NotNull Map<? extends Integer, ? extends String> m) {

    }

    public void clear() {

    }

    @NotNull
    public Set<Integer> keySet() {
        return null;
    }

    @NotNull
    public Collection<String> values() {
        return null;
    }

    @NotNull
    public Set<Entry<Integer, String>> entrySet() {
        return null;
    }

    public boolean equals(Object o) {
        return false;
    }

    public int hashCode() {
        return 0;
    }
}
