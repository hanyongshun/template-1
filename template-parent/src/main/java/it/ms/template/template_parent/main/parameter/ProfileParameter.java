package it.ms.template.template_parent.main.parameter;

import it.ms.template.template_parent.main.Consts;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;

public class ProfileParameter implements ParameterHandler {

	@Parameter(names = { "-ap", "--activeprofile" }, description = "Active Profile [prod|dev]", converter = ProfileConverter.class)
	private Profile activeProfile = Profile.prod;

	public String getActiveProfile() {
		return activeProfile.name();
	}

	public static class ProfileConverter implements IStringConverter<Profile> {
		@Override
		public Profile convert(String value) {
			return Profile.valueOf(value);
		}
	}

	@Override
	public void parameterAction() {
		System.setProperty(Consts.ACTIVE_PROFILES_KEY, getActiveProfile());
	}

	public String toString() {
		return "Active Startup Profile : " + getActiveProfile();
	}

	private enum Profile {
		dev,
		prod
	}
}
