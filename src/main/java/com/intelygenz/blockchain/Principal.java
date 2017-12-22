package com.intelygenz.blockchain;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(false, "0000");
        blockchain.addBlock("Second Block");
        blockchain.addBlock("Third Block");
        for (int i = 0; i < 3; i++) {
            blockchain.addBlock("Block " + i);    
        }
        List<String> blocks = blockchain.getBlocks();
        for (String block : blocks) {
            System.out.println(block);
        }
    }
}
