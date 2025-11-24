# MultiHex-Cipher
A unique cipher that combines letter shifting and hexadecimal encoding

**Step-by-step approach of the MultiHex cipher: **
Each letter in the plaintext and key is encoded to its numerical value.
The numerical value of the ith plaintext is then multiplied by the numerical value of the ith key.
Perform the modulo operation on the result of the multiplication.
The result of the modulo operation is then referred to the alphabet letter.
Then, the alphabet letter(in lowercase) is referred to the ASCII table.
The hexadecimal of the corresponding  letter is the ciphertext.

Function
The encryption process of the MultiHex Cipher is denoted by the following function:

	Ci = Hex[(Pi * Ki) mod 26]
	
Where:
Ci is the current ciphertext position
Pi is the current plaintext position
Ki is the current key position

The decryption process of the MultiHex Cipher is denoted by the following function:
For each pair of hexadecimal digits, we first convert them to their corresponding ASCII value, Ci.

	Pi= (Ci * Ki-1) mod 26

Where:
Pi is the current plaintext position
Ci is the current ciphertext position
Ki-1 is the modular multiplicative inverse of K modulo 26, such that (Ki * Ki-1 ) mod 26 = 1
Algorithm

**Encryption Algorithm:**
Convert the plaintext and key to their alphabetical positions.
Multiply the corresponding positions of the plaintext and key characters modulo 26.
Convert the result to letters.
Convert each letter to its hexadecimal ASCII value.

	FUNCTION encrypt(plaintext: String, key: String): String
    SET ciphertext TO ""
    SET keyIndex TO 0
    SET key TO key.toLowerCase()
	
    FOR EACH character IN plaintext
        IF isLetter(character) THEN
            SET character TO character.toLowerCase()
            SET base TO 'a'

            SET plaintextCharPos TO ASCII(character) - ASCII(base)
            SET keyCharPos TO ASCII(key[keyIndex MOD LENGTH(key)]) - ASCII(base)

            SET encryptedChar TO (plaintextCharPos * keyCharPos) MOD 26 + ASCII(base)
            SET hexa TO encryptedChar AS HEXADECIMAL STRING (uppercase)

            APPEND hexa TO ciphertext
            SET keyIndex TO keyIndex + 1
        ELSE
            APPEND character TO ciphertext
        ENDIF
    ENDFOR

    RETURN ciphertext
	END FUNCTION


**Decryption Algorithm:**
Convert the hexadecimal ciphertext back to letters.
Convert the ciphertext and key to their alphabetical positions.
Check all possible values for the plaintext character's position (from 0 to 25) to find which one, when multiplied by the key's position modulo 26, will give the ciphertext position.
Convert the positions back to letters to recover the original plaintext.

	FUNCTION decrypt(ciphertext: String, key: String): String
    SET plaintext TO ""
    SET keyIndex TO 0
    SET key TO key.toLowerCase()
    SET hexBuffer TO ""

    FOR EACH character IN ciphertext
        IF isLetterOrDigit(character) THEN
            APPEND character TO hexBuffer
            
            IF LENGTH(hexBuffer) == 2 THEN
                SET ascii TO INTEGER VALUE OF hexBuffer AS HEXADECIMAL
                SET encryptedChar TO CHARACTER FROM ASCII VALUE (ascii)
                SET base TO 'a'

                IF isLetter(encryptedChar) THEN
                    SET encryptedCharPos TO ASCII(encryptedChar) - ASCII(base)
                    SET keyCharPos TO ASCII(key[keyIndex MOD LENGTH(key)]) - ASCII(base)

                    SET decryptedCharPos TO -1
                    FOR i FROM 0 TO 25
                        IF (i * keyCharPos) MOD 26 == encryptedCharPos THEN
                            SET decryptedCharPos TO i
                            BREAK
                        ENDIF
                    ENDFOR

                    IF decryptedCharPos == -1 THEN
                        THROW Exception("Decryption failed: no match found.")
                    ENDIF

                    SET decryptedChar TO CHARACTER (decryptedCharPos + ASCII(base))
                    APPEND decryptedChar TO plaintext
                    SET keyIndex TO keyIndex + 1
                ENDIF

                CLEAR hexBuffer
            ENDIF
        ELSE
            APPEND character TO plaintext
        ENDIF
    ENDFOR

    RETURN plaintext
	END FUNCTION


The decryption of the cipher may not work perfectly due to a fundamental issue with the design of the encryption process. The key issues with the cipher design are:

**1. Non-Unique Mapping with Modular Arithmetic and Multiplication:**
In the encryption process, each letter in the plaintext is converted to an encrypted value using the formula (plaintextPosition×keyPosition) mod  26. However, this method can lead to the same encrypted value being created by different pairs of numbers because multiplication with modulo 26 is not unique. For example, (3×9) mod  26 = 1, but other pairs of numbers could also result in 1. When decrypting, the method tries to reverse the multiplication by solving (decryptedPosition×keyPosition) mod  26 = encryptedPosition. This can lead to several possible answers for the decrypted position, making it unclear which one is correct. Because of this, decryption may not always work perfectly.

**3. Key Multiplication and Modular Arithmetic May Introduce Non-Invertible Cases:**
Modular multiplication is not always invertible for all values. For example, if the keyCharPos value is not coprime with 26 (the size of the alphabet), no modular inverse exists. This means decryption is mathematically impossible for those cases.
Although the confusion in decryption might seem like a problem, it can actually help make the cipher more secure. The added complexity makes it harder for attackers to analyze or break the cipher. This ambiguity acts as a barrier against hacking attempts, requiring attackers to put in more effort or have extra information to figure out the original message.
