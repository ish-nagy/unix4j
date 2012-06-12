package org.unix4j.impl;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import org.unix4j.util.TypedMap;

public class AbstractArgs<O extends Enum<O>> {
	private final TypedMap args;
	private final EnumSet<O> opts;
	
	public AbstractArgs(Class<O> optClass) {
		args = new TypedMap();
		opts = EnumSet.noneOf(optClass);
	}
	
	public <T> void setArg(TypedMap.Key<T> key, T value) {
		args.put(key, value);
	}
	public <T> T getArg(TypedMap.Key<T> key) {
		return args.get(key);
	}
	public boolean hasArg(TypedMap.Key<?> key) {
		return args.containsKey(key);
	}
	public TypedMap getArgs() {
		return args;
	}
	public void setOpt(O opt) {
		opts.add(opt);
	}
	public void setOpts(O... opts) {
		for (final O opt : opts) {
			setOpt(opt);
		}
	}
	public boolean hasOpt(O opt) {
		return opts.contains(opt);
	}
	public Set<O> getOpts() {
		return opts;
	}
	@Override
	public String toString() {
		if (args.isEmpty() && opts.isEmpty()) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		for (Map.Entry<TypedMap.Key<?>, ?> e : args.entrySet()) {
			if (sb.length() > 0) sb.append(' ');
			sb.append("-").append(e.getKey().getKey());
			sb.append(" ").append(e.getValue());
		}
		for (final O opt : opts) {
			if (sb.length() > 0) sb.append(' ');
			sb.append("-").append(opt);
		}
		return sb.toString();
	}
}
