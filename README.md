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

**Decryption Algorithm:**
Convert the hexadecimal ciphertext back to letters.
Convert the ciphertext and key to their alphabetical positions.
Check all possible values for the plaintext character's position (from 0 to 25) to find which one, when multiplied by the key's position modulo 26, will give the ciphertext position.
Convert the positions back to letters to recover the original plaintext.

The decryption of the cipher may not work perfectly due to a fundamental issue with the design of the encryption process. The key issues with the cipher design are:

**1. Non-Unique Mapping with Modular Arithmetic and Multiplication:**
In the encryption process, each letter in the plaintext is converted to an encrypted value using the formula (plaintextPosition×keyPosition) mod  26. However, this method can lead to the same encrypted value being created by different pairs of numbers because multiplication with modulo 26 is not unique. For example, (3×9) mod  26 = 1, but other pairs of numbers could also result in 1. When decrypting, the method tries to reverse the multiplication by solving (decryptedPosition×keyPosition) mod  26 = encryptedPosition. This can lead to several possible answers for the decrypted position, making it unclear which one is correct. Because of this, decryption may not always work perfectly.

**3. Key Multiplication and Modular Arithmetic May Introduce Non-Invertible Cases:**
Modular multiplication is not always invertible for all values. For example, if the keyCharPos value is not coprime with 26 (the size of the alphabet), no modular inverse exists. This means decryption is mathematically impossible for those cases.
Although the confusion in decryption might seem like a problem, it can actually help make the cipher more secure. The added complexity makes it harder for attackers to analyze or break the cipher. This ambiguity acts as a barrier against hacking attempts, requiring attackers to put in more effort or have extra information to figure out the original message.
