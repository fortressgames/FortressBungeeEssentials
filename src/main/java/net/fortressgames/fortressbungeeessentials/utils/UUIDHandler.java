package net.fortressgames.fortressbungeeessentials.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;

public class UUIDHandler {

	@SneakyThrows
	public static void getUUIDFromName(ResultCallBack<String> callBack, String username) {
		String url = "https://api.mojang.com/users/profiles/minecraft/" + username;
		String UUIDJson = IOUtils.toString(new URL(url));

		if(UUIDJson.isEmpty()) callBack.callBack(false, null);

		JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);

		String[] uuidSplit = UUIDObject.get("id").toString().split("");

		callBack.callBack(true,
				uuidSplit[0] + uuidSplit[1] + uuidSplit[2] + uuidSplit[3] + uuidSplit[4] + uuidSplit[5] + uuidSplit[6] +uuidSplit[7] + "-" +
						uuidSplit[8] + uuidSplit[9] + uuidSplit[10] + uuidSplit[11] + "-" +
						uuidSplit[12] + uuidSplit[13] + uuidSplit[14] + uuidSplit[15] + "-" +
						uuidSplit[16] + uuidSplit[17] + uuidSplit[18] + uuidSplit[19] + "-" +
						uuidSplit[20] + uuidSplit[21] + uuidSplit[22] + uuidSplit[23] + uuidSplit[24] + uuidSplit[25] + uuidSplit[26] + uuidSplit[27] + uuidSplit[28] + uuidSplit[29] + uuidSplit[30] + uuidSplit[31]);
	}

	@FunctionalInterface
	public interface ResultCallBack<T> {
		void callBack(boolean successful, T uuid);
	}
}