# Approximate Dictionary Chunker #

chunkdict.java implements a dictionary chunker which applies approximate matching using a fixed weighted edit distance threshold.

The dictionary should be tab-separated like the following:
```
key1  value1 value2 value3
key2  value1 value2 
key3  value1 value2 value3
```
The code can then apply approx dictionary chunking to a group of input .txt documents.


It requires the lingpipe jar to run which can found [here](http://alias-i.com/lingpipe/web/download.html).
