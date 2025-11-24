# MultiHex-Cipher
Create a unique cipher that combines letter shifting and hexadecimal encoding

The decryption of the cipher may not work perfectly due to a fundamental issue with the design of the encryption process. The key issues with the cipher design are:

**1. Non-Unique Mapping with Modular Arithmetic and Multiplication**
In the encryption process, each letter in the plaintext is converted to an encrypted value using the formula (plaintextPosition×keyPosition) mod  26. However, this method can lead to the same encrypted value being created by different pairs of numbers because multiplication with modulo 26 is not unique. For example, (3×9) mod  26 = 1, but other pairs of numbers could also result in 1. When decrypting, the method tries to reverse the multiplication by solving (decryptedPosition×keyPosition) mod  26 = encryptedPosition. This can lead to several possible answers for the decrypted position, making it unclear which one is correct. Because of this, decryption may not always work perfectly.

**3. Key Multiplication and Modular Arithmetic May Introduce Non-Invertible Cases**
Modular multiplication is not always invertible for all values. For example, if the keyCharPos value is not coprime with 26 (the size of the alphabet), no modular inverse exists. This means decryption is mathematically impossible for those cases.
Although the confusion in decryption might seem like a problem, it can actually help make the cipher more secure. The added complexity makes it harder for attackers to analyze or break the cipher. This ambiguity acts as a barrier against hacking attempts, requiring attackers to put in more effort or have extra information to figure out the original message.
