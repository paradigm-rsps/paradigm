/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package rs;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Reflection
{
	private static final boolean PRINT_DEBUG_MESSAGES = true;

	private static final Map<String, Class<?>> classes = new HashMap<>();

	static
	{
		try
		{
			Enumeration<URL> systemResources = ClassLoader.getSystemResources("");

			while (systemResources.hasMoreElements())
			{
				URL url = systemResources.nextElement();

				Path path;
				try
				{
					path = new File(url.toURI())
						.toPath();
				}
				catch (URISyntaxException e)
				{
					path = new File(url.getPath())
						.toPath();
				}

				Files.walk(path)
					.filter(Files::isRegularFile)
					.forEach(f ->
					{
						String className = f
							.getName(f.getNameCount() - 1)
							.toString()
							.replace(".class", "");
					});
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Class<?> findClass(String name) throws ClassNotFoundException
	{
		return Class.forName(name);
	}

	public static Field findField(Class<?> clazz, String name) throws NoSuchFieldException
	{
		return clazz.getDeclaredField(name);
	}

	public static String getMethodName(Method method)
	{
		return method.getName();
	}

	public static Class<?>[] getParameterTypes(Method method)
	{
		return method.getParameterTypes();
	}

	public static int getInt(Field field, Object obj) throws IllegalArgumentException, IllegalAccessException
	{
		boolean unset = false;
		if ((field.getModifiers() & Modifier.PRIVATE) == 0)
		{
			// we're outside of the package so set it accessable
			// to behave like we are in the package
			field.setAccessible(true);
			unset = true;
		}

		int i;
		try
		{
			i = field.getInt(obj);
		} finally
		{
			if (unset)
			{
				field.setAccessible(false);
			}
		}

		return i;
	}

	public static void setInt(Field field, Object obj, int value) throws IllegalArgumentException, IllegalAccessException
	{
		boolean unset = false;
		if ((field.getModifiers() & Modifier.PRIVATE) == 0)
		{
			// we're outside of the package so set it accessable
			// to behave like we are in the package
			field.setAccessible(true);
			unset = true;
		}

		try
		{
			field.setInt(obj, value);
		}
		finally
		{
			if (unset)
			{
				field.setAccessible(false);
			}
		}
	}

	public static BigInteger modInverse(BigInteger val, int bits)
	{
		BigInteger shift = BigInteger.ONE.shiftLeft(bits);
		return val.modInverse(shift);
	}

	public static int modInverse(int val)
	{
		return modInverse(BigInteger.valueOf(val), 32).intValue();
	}

	public static Object invoke(Method method, Object object, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		return method.invoke(object, args);
	}
}