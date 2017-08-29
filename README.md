# compression_encryptor

Use the program to convert text file of any format into compressed and encrypted binary file. Encryption requires a key which the user must remember to decrypt the files again otherwise there is no way to decrypt it back.

Compression & Encryption
------------------------
1. Put all the three java files in a folder.
2. Put the file you want to compress and encrypt in that folder too.
3. Compile ->
    javac encode.java huffman.java
4. Now Execute ->
    java encode <filename with extension>
5. You will be prompted to enter a key. Please enter a key for your enryption and remember the key.
6. Two files namely table.txt and coded.txt will be created which will be your encrypted data. keep this two files safe.

Extraction & Decryption
-----------------------
1. Put all the three java files in a folder.
2. Put the two files namely coded.txt and table.txt that you must have generated while encrypting your data.
3. Compile ->
    javac decode.java huffman.java
4. Execute ->
    java decode <output filename with extension>
5. You will be prompted to enter a key. Please enter the same key you entered for your enryption.
6. If the key entered is wrong, the decrypted data will be gibberish.
7. The output file will be present in the same folder, decrepyted and extracted.

Contributors
------------
Prakhar Ganesh
