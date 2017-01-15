package com.teamacronymcoders.base.api;

import java.util.LinkedHashMap;

public interface IDebuggable {
	LinkedHashMap<String, String> getDebugStrings(LinkedHashMap<String, String> debugStrings);
}
