package fightinggame.utils.json.users;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fightinggame.users.User;
import fightinggame.users.UserData;
import fightinggame.users.UserId;

public class UserModule extends SimpleModule {
    private static final String NAME = "UserModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {
    };

    /**
     * Initializes this UserModule with appropriate serializers and deserializers.
     */
    public UserModule() {
        super(NAME, VERSION_UTIL.version());

        addSerializer(UserData.class, new UserDataSerializer());
        addDeserializer(UserData.class, new UserDataDeserializer());

        addSerializer(UserId.class, new UserIdSerializer());
        addDeserializer(UserId.class, new UserIdDeserializer());

        addSerializer(User.class, new UserSerializer());
        addDeserializer(User.class, new UserDeserializer());
    }
}

