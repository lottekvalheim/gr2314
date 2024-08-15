package persistence.internal;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.User;
import core.Users;
import core.Dresses;

public class CoutureModule extends SimpleModule {
    /**
     * Static name.
     */
    private static final String NAME = "CoutureModule";
    /**
     * Serial version UID.
     */
    public CoutureModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(User.class, new UserSerializer());
        addDeserializer(User.class, new UserDeserializer());

        addSerializer(Users.class, new UsersSerializer());
        addDeserializer(Users.class, new UsersDeserializer());

        addSerializer(Dresses.class, new DressesSerializer());
        addDeserializer(Dresses.class, new DressesDeserializer());
    }
}