import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.dict.DictionaryEntry;
import com.aliasi.dict.TrieDictionary;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.dict.ApproxDictionaryChunker;
import com.aliasi.spell.FixedWeightEditDistance;
import com.aliasi.spell.WeightedEditDistance;
import java.io.*;
import java.util.*;


class chunkdict {
    public static void main(String[] args) throws IOException {

        // define path and reader for input dictionary file
        BufferedReader br = new BufferedReader (new FileReader("input/dictionary.txt"));
        TrieDictionary<String> dictionary = new TrieDictionary<String>();
        String line;
 
        // read dictionary file and split into keys and values
        while ((line = br.readLine())!=null) {
            String KEY = line.split("\t")[0];
            String VAL = line.split("\t")[1];
            String[] VAL1 = VAL.split("\\|");
            
            // build dictionary
            for (String i : VAL1) {
                dictionary.addEntry(new DictionaryEntry<String>(i, KEY));
            }
        }
      
        // define tokenizer, distance measure and dictionary chunker
        TokenizerFactory tokenizerFactory
            = IndoEuropeanTokenizerFactory.INSTANCE;
        WeightedEditDistance editDistance
            = new FixedWeightEditDistance(0,-1.0,-1.0,-1.0,Double.NaN);
        double maxDistance = 2.0;
        ApproxDictionaryChunker dictionaryChunker = new ApproxDictionaryChunker(dictionary,
            tokenizerFactory,
            editDistance,maxDistance);
         
        // define input directory for the txt documents
        String docs_dir = "input/docs/";
        File dir = new File(docs_dir);
        File[] files = dir.listFiles();
        StringBuilder sb = new StringBuilder();

        // define results output file
        PrintStream o = new PrintStream(new File("out.txt"));
        System.setOut(o);
        System.out.println("Key" + "\t" + "Phrase" + "\t" + "Score");

        // append text from directory of documents
        for (File f : files) {
            BufferedReader inputStream = new BufferedReader(new FileReader(f));
            String text;
            while ((text = inputStream.readLine())!=null){
                sb.append(text);
            }
         }
        
          // chunk the text, record the score and save to file
          Chunking chunking = dictionaryChunker.chunk(sb);
    
          for (Chunk chunk : chunking.chunkSet()) {
              int start = chunk.start();
              int end = chunk.end();
              String type = chunk.type();
              double score = chunk.score();
              String phrase = sb.substring(start,end);
              System.setOut(o); 
              System.out.println(type + "\t" +  phrase + "\t" + score);
         }           
    }
}

