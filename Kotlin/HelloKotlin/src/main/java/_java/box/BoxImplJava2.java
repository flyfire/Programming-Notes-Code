package _java.box;

import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BoxImplJava2 implements BoxIf2 {

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

    @Override
    public String getOrDefault(Object key, String defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super Integer, ? super String> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super Integer, ? super String, ? extends String> function) {

    }

    @Override
    public String putIfAbsent(Integer key, String value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(Integer key, String oldValue, String newValue) {
        return false;
    }

    @Override
    public String replace(Integer key, String value) {
        return null;
    }

    @Override
    public String computeIfAbsent(Integer key, Function<? super Integer, ? extends String> mappingFunction) {
        return null;
    }

    @Override
    public String computeIfPresent(Integer key, BiFunction<? super Integer, ? super String, ? extends String> remappingFunction) {
        return null;
    }

    @Override
    public String compute(Integer key, BiFunction<? super Integer, ? super String, ? extends String> remappingFunction) {
        return null;
    }

    @Override
    public String merge(Integer key, String value, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return null;
    }
}
