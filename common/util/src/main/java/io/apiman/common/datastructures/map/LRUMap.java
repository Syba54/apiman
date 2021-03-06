/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apiman.common.datastructures.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Marc Savy {@literal <msavy@redhat.com>}
 * @param <K> Key
 * @param <V> Value
 */
public abstract class LRUMap<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 8404941290861582868L;
    private int maxElems;

    public LRUMap(int initialCapacity, float loadFactor, int maxElems) {
        super(initialCapacity, loadFactor);
        this.maxElems = maxElems;
    }

    public LRUMap(int initialCapacity, int maxElems) {
        super(initialCapacity);
        this.maxElems = maxElems;
    }

    public LRUMap(int maxElems) {
        super();
        this.maxElems = maxElems;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > maxElems) {
            handleRemovedElem(eldest);
            return true;
        }
        return false;
    }

    protected abstract void handleRemovedElem(Map.Entry<K, V> eldest);
}
