# Approximate Dictionary Chunker #

chunkdict.java implements a dictionary chunker which applies approximate matching using a fixed weighted edit distance threshold.

It expects the following format for the dictionary:
```
key1  value1|value2|value3
key2  value1|value2 
key3  value1|value2|value3
```
The code can then apply approximate dictionary chunking to a group of input .txt documents.


It requires the lingpipe jar to run which can found [here](http://alias-i.com/lingpipe/web/download.html).
