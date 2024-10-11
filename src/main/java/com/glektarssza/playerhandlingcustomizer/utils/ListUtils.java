package com.glektarssza.playerhandlingcustomizer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

/**
 * A utility class for filtering lists.
 */
public class ListUtils {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> int findIndex(List<T> list, Predicate<T> predicate) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int findIndex(List<T> list, Predicate<T> predicate,
        int startIndex) {
        for (int i = startIndex; i < list.size(); i++) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int reversefindIndex(List<T> list, Predicate<T> predicate,
        int startIndex) {
        for (int i = startIndex; i > 0; i--) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return i;
            }
        }
        return -1;
    }

    @Nullable
    public static <T> T find(List<T> list, Predicate<T> predicate) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    @Nullable
    public static <T> T find(List<T> list, Predicate<T> predicate,
        int startIndex) {
        for (int i = startIndex; i < list.size(); i++) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    @Nullable
    public static <T> T reverseFind(List<T> list, Predicate<T> predicate) {
        for (int i = list.size() - 1; i > 0; i--) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    @Nullable
    public static <T> T reverseFind(List<T> list, Predicate<T> predicate,
        int startIndex) {
        for (int i = startIndex; i > 0; i--) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }
}
