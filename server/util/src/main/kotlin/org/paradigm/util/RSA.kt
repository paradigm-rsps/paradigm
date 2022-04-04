package org.paradigm.util

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.io.pem.PemObject
import org.bouncycastle.util.io.pem.PemReader
import org.bouncycastle.util.io.pem.PemWriter
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileNotFoundException
import java.math.BigInteger
import java.nio.file.Files
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.Security
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec

class RSA {

    lateinit var privateExponent: BigInteger private set
    lateinit var privateModulus: BigInteger private set

    fun load() {
        val privateKeyFile = File("data/rsa/private.pem")

        if(!privateKeyFile.exists()) {
            throw FileNotFoundException("Could not locate the RSA private key file at: data/rsa/private.pem. Please re-run the server setup command.")
        }

        PemReader(Files.newBufferedReader(privateKeyFile.toPath())).use {
            val pem = it.readPemObject()
            val keySpec = PKCS8EncodedKeySpec(pem.content)

            Security.addProvider(BouncyCastleProvider())

            val factory = KeyFactory.getInstance("RSA", "BC")
            val privateKey = factory.generatePrivate(keySpec) as RSAPrivateKey

            privateExponent = privateKey.privateExponent
            privateModulus = privateKey.modulus
        }
    }

    companion object {
        private const val KEY_SIZE = 2048
        private const val RADIX = 16

        fun generateKeyPair() {
            Logger.info("Generating new RSA private/public key-pair...")

            val rsaDir = File("data/rsa/")

            Security.addProvider(BouncyCastleProvider())

            val generator = KeyPairGenerator.getInstance("RSA", "BC")
            generator.initialize(KEY_SIZE)

            val keypair = generator.generateKeyPair()
            val publicKey = keypair.public as RSAPublicKey
            val privateKey = keypair.private as RSAPrivateKey

            Logger.info("Saving RSA public key to file.")
            PemWriter(Files.newBufferedWriter(rsaDir.resolve("public.pem").toPath())).use {
                it.writeObject(PemObject("RSA PUBLIC KEY", publicKey.encoded))
            }

            Logger.info("Saving RSA private key to file.")
            PemWriter(Files.newBufferedWriter(rsaDir.resolve("private.pem").toPath())).use {
                it.writeObject(PemObject("RSA PRIVATE KEY", privateKey.encoded))
            }

            Logger.info("Saving RSA public modulus to file.")
            Files.newBufferedWriter(rsaDir.resolve("modulus.txt").toPath()).use {
                it.write(publicKey.modulus.toString(RADIX))
            }

            Logger.info("Successfully generated and saved the new RSA private/public key-pair files.")
        }
    }
}