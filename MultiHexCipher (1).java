/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adilazahid
 */
import java.util.Scanner;

public class MultiHexCipher {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String key = "crypto";
        //System.out.print("Enter key: ");
        //String key = scanner.nextLine();

            System.out.print("\nEnter plaintext: ");
            String plaintext = scanner.nextLine();
            
            String ciphertext = encrypt(plaintext, key);
            System.out.println("\nCiphertext: " + ciphertext);
            String decrypted = decrypt(ciphertext, key);
            System.out.println("\nPlaintext: " + decrypted);
        
    }
    
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;
        key = key.toLowerCase();
        
        for (char character : plaintext.toCharArray()) {
            if (Character.isLetter(character)) {
                character = Character.toLowerCase(character);
                char base = 'a';

                int plaintextCharPos = character - base;
                int keyCharPos = key.charAt(keyIndex % key.length()) - base;

                int encryptedChar = (plaintextCharPos * keyCharPos) % 26 + base;
                String hexa = Integer.toHexString(encryptedChar).toUpperCase();
                
                ciphertext.append(hexa);

                keyIndex++; // Move to the next character in the key
            } else {
                ciphertext.append(character); // Keep non-letter characters as is
            }
        }
        
        return ciphertext.toString();
    }
    
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyIndex = 0;
        key = key.toLowerCase();
        
        StringBuilder hexBuffer = new StringBuilder();
        
for (char character : ciphertext.toCharArray()) {
        if (Character.isLetterOrDigit(character)) {
            hexBuffer.append(character); // Collect hex digits
            
            if (hexBuffer.length() == 2) { // Once two hex digits are collected
                // Convert hex to character
                int ascii = Integer.parseInt(hexBuffer.toString(), 16);
                char encryptedChar = (char) ascii;
                char base = 'a';

                // Decrypt the character if it is a letter
                if (Character.isLetter(encryptedChar)) {
                    int encryptedCharPos = encryptedChar - base;
                    int keyCharPos = key.charAt(keyIndex % key.length()) - base;

                    // Reverse the multiplication by brute force
                    int decryptedCharPos = -1;
                    for (int i = 0; i < 26; i++) {
                        if ((i * keyCharPos) % 26 == encryptedCharPos) {
                            decryptedCharPos = i;
                            break;
                        }
                    }

                    if (decryptedCharPos == -1) {
                        throw new IllegalArgumentException("Decryption failed: no match found.");
                    }

                    char decryptedChar = (char) (decryptedCharPos + base);
                    plaintext.append(decryptedChar);
                    keyIndex++;
                }

                hexBuffer.setLength(0); // Clear buffer after processing
            }
        } else {
            plaintext.append(character); // Append non-alphabetic characters directly
        }
    }

    return plaintext.toString();
    }
}

