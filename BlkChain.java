import java.util.ArrayList;

public class BlkChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        System.out.println("Mining block 1...");
        Block block1 = new Block("Hi I'm the first block", "0");
        block1.mineBlock(difficulty);
        blockchain.add(block1);

        System.out.println("Mining block 2...");
        Block block2 = new Block("Yo, second block here", block1.hash);
        block2.mineBlock(difficulty);
        blockchain.add(block2);

        System.out.println("Mining block 3...");
        Block block3 = new Block("Hey, I'm the third block", block2.hash);
        block3.mineBlock(difficulty);
        blockchain.add(block3);

        System.out.println("\nBlockchain Valid: " + isChainValid());

        System.out.println("\nBlockchain:");
        for (Block block : blockchain) {
            System.out.println("Block:");
            System.out.println("  Hash: " + block.hash);
            System.out.println("  Previous Hash: " + block.previousHash);
            System.out.println("  Data: " + block.getData());
            System.out.println("  TimeStamp: " + block.getTimeStamp());
            System.out.println("  Nonce: " + block.getNonce());
            System.out.println();
        }
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes not equal");
                return false;
            }

            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("Block not mined");
                return false;
            }
        }
        return true;
    }
}
