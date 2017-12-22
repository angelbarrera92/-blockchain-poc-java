package com.intelygenz.blockchain;

import java.util.LinkedList;
import java.util.List;

public class Blockchain {

    private boolean      debug     = false;
    private String       difficult = "0";
    private List<String> blocks;

    public Blockchain(boolean debug, String difficult) {
        this.debug = debug;
        this.difficult = difficult;
        this.blocks = new LinkedList<>();
        this.doGenesis();
    }

    private void doGenesis() {
        final int index = 0;
        final String prevHash = "0";
        final long timestamp = System.currentTimeMillis();
        final String data = "GENESIS-BLOCK";
        this.doHash(data, timestamp, prevHash, index);
    }

    private void doHash(String data, long timestamp, String prevHash, int index) {
        int nonce = 0;
        String hash = "";
        while (!this.isValidHash(hash)) {
            final String input = String.format("%s%s%s%s%s", data, timestamp + "", prevHash, index + "", nonce);
            hash = Enconding.digest("SHA-256", input);
            nonce++;
            if (this.debug) {
                System.out.println("Hash: " + hash);
                System.out.println("Nonce: " + nonce);
            }
        }
        this.blocks.add(hash);
    }

    private boolean isValidHash(String hash) {
        return hash.startsWith(this.difficult);
    }

    private String getLastHash() {
        return this.blocks.get(this.blocks.size() - 1);
    }

    public void addBlock(String data) {
        final int index = this.blocks.size();
        final String prevHash = this.getLastHash();
        final long timestamp = System.currentTimeMillis();
        this.doHash(data, timestamp, prevHash, index);
    }

    public List<String> getBlocks() {
        return this.blocks;
    }
}
