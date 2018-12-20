package WatchTawer;



import java.nio.charset.StandardCharsets;

import java.security.SecureRandom;



import javax.crypto.Cipher;

import javax.crypto.KeyGenerator;

import javax.crypto.SecretKey;

import javax.crypto.spec.IvParameterSpec;



    public class Encrypt {

        private SecretKey key;

        private IvParameterSpec iv;

        private Cipher cipher;


        private void keygen() {

            byte[] ivByte = new byte[16];

            try {

                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

                SecureRandom random = new SecureRandom();

                keyGenerator.init(256, random);

                random.nextBytes(ivByte);

                key = keyGenerator.generateKey();

                iv = new IvParameterSpec(ivByte);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }


        public byte[] encrypt(String password) {

            byte[] plainText = password.getBytes(StandardCharsets.UTF_8);

            try {

                keygen();

                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

                cipher.init(Cipher.ENCRYPT_MODE, key, iv);

                return cipher.doFinal(plainText);

            } catch (Exception e) {

                e.printStackTrace();

            }

            return new byte[128];

        }


        public String decrypt(byte[] cipherText) throws Exception {

            try {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);

            } catch (Exception e) {

                e.printStackTrace();

            }
            return new String();


        }

    }

