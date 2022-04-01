package tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void test插入word后应该可以查询前缀() {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");

        assertTrue(trie.search("app"));
        assertTrue(trie.search("apple"));
        assertTrue(trie.startsWith("app"));

        assertFalse(trie.search("banana"));
        assertFalse(trie.startsWith("x"));
        assertFalse(trie.search("appx"));
    }

}