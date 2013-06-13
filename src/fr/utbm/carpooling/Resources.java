package fr.utbm.carpooling;


import fr.utbm.carpooling.model.*;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

import android.content.Context;

public class Resources {

	private static Map<Integer, Color> colors = new HashMap<Integer, Color>();
	private static Map<Integer, Trunk> trunks = new HashMap<Integer, Trunk>();
	private static Map<Integer, SiteShort> sitesShort = new HashMap<Integer, SiteShort>();
	private static Map<Integer, Brand> brands = new HashMap<Integer, Brand>();
	private static Map<Integer, Map<Integer, Model>> models = new HashMap<Integer, Map<Integer, Model>>();
	private static List<Color> colorsSorted;
	private static User mUser = null;
	private static HashMap<String, DriverCar> mCars = new HashMap<String, DriverCar>();

	public static void init() {
		//    	User user1 = new User();
		//    	user1.setUserId("plop");
		//        user1.setApiToken("fze8f54zef4ze56f4ezf8z4ef5zef8zef4z8ef4z8e9f4e8fz89f");
		//        user1.setEmail("plop@utbm.fr");
		//        user1.setFirstname("Truc");
		//        user1.setLastname("Chose");
		//        user1.setPhone("0667849494");
		//setUser(user1);

		colors.put(0, new Color(0, "", "000000"));
		colors.put(1, new Color(1, "", "808080"));
		colors.put(2, new Color(2, "", "FFFFFF"));
		colors.put(3, new Color(3, "", "000080"));
		colors.put(4, new Color(4, "", "0000FF"));
		colors.put(5, new Color(5, "", "008000"));
		colors.put(6, new Color(6, "", "00FF00"));
		colors.put(7, new Color(7, "", "800000"));
		colors.put(8, new Color(8, "", "FF0000"));
		colors.put(9, new Color(9, "", "FFFF00"));
		colors.put(10, new Color(10, "", "00FFFF"));
		colors.put(11, new Color(11, "", "FF00FF"));

		colorsSorted = new ArrayList<Color>();
		colorsSorted.addAll(colors.values());
		Collections.sort(colorsSorted);

		trunks.put(0, new Trunk(0, "tiny"));
		trunks.put(1, new Trunk(1, "small"));
		trunks.put(2, new Trunk(2, "regular"));
		trunks.put(3, new Trunk(3, "large"));
		trunks.put(4, new Trunk(4, "huge"));

		sitesShort.put(0, new SiteShort(0, "belfort"));
		sitesShort.put(1, new SiteShort(1, "sévenans"));
		sitesShort.put(2, new SiteShort(2, "montbéliard"));
		sitesShort.put(3, new SiteShort(3, "sbarro"));

		brands.put(0, new Brand(0, "renault"));
		brands.put(1, new Brand(1, "peugeot"));
		brands.put(2, new Brand(2, "citroën"));


		Map<Integer, Model> renault_models = new HashMap<Integer, Model>();

		renault_models.put(0, new Model(0, 0, "clio"));
		renault_models.put(1, new Model(0, 1, "twingo"));
		renault_models.put(2, new Model(0, 2, "mégane"));
		renault_models.put(3, new Model(0, 3, "espace"));

		Map<Integer, Model> peugeot_models = new HashMap<Integer, Model>();

		peugeot_models.put(0, new Model(1, 0, "206"));
		peugeot_models.put(1, new Model(1, 1, "306"));
		peugeot_models.put(2, new Model(1, 2, "406"));
		peugeot_models.put(3, new Model(1, 3, "307"));

		Map<Integer, Model> citroen_models = new HashMap<Integer, Model>();

		citroen_models.put(0, new Model(2, 0, "C1"));
		citroen_models.put(1, new Model(2, 1, "C2"));
		citroen_models.put(2, new Model(2, 2, "C3"));
		citroen_models.put(3, new Model(2, 3, "C4"));


		models.put(0, renault_models);
		models.put(1, peugeot_models);
		models.put(2, citroen_models);
	}

	public static User getUser() {
		return mUser;
	}

	public static List<Color> getColors() {
		return colorsSorted;
	}

	public static Color getColor(int id) {
		return colors.get(id);
	}

	public static List<Trunk> getTrunks() {
		return new ArrayList<Trunk>(trunks.values());
	}

	public static Trunk getTrunk(int id) {
		return trunks.get(id);
	}

	public static List<SiteShort> getSitesShort() {
		return new ArrayList<SiteShort>(sitesShort.values());
	}

	public static SiteShort getSiteShort(int id) {
		return sitesShort.get(id);
	}

	public static List<Brand> getBrands() {
		return new ArrayList<Brand>(brands.values());
	}

	public static Brand getBrand(int id) {
		return brands.get(id);
	}

	public static List<Model> getModels(int brand_id) {
		return new ArrayList<Model>(models.get(brand_id).values());
	}

	public static Model getModel(int brand_id, int id) {
		return models.get(brand_id).get(id);
	}

	public static void setUserInfos(UserInfos object, Context context) {
		setUser(object.getUser());
		setCars(object.getCars());
		saveCars(context);
		saveUser(context);
	}

	public static void setUser(UserShort object) {
		mUser.setEmail(object.getEmail());
		mUser.setPhone(object.getPhone());
		mUser.setFirstname(object.getFirstname());
		mUser.setLastname(object.getLastname());
		mUser.setUserId(object.getUserId());
	}

	public static void setCars(ArrayList<DriverCar> object) {
		String cars = "[";
		if (object != null && !object.isEmpty()) {
			for(DriverCar c : object) {
				mCars.put(c.getId(), c);
				cars += "{ " + c.serializeJSON() + " },";
			}
			cars = cars.substring(0, cars.length() - 2);
		}
		cars += "]";
	}

	public static void saveUser(Context c) {
		try {
			FileManager.writeFile("{ " + mUser.serializeJSON() + " }", c.openFileOutput(Constants.FILE_USER_INFO, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void saveCars(Context c) {
		String cars = "[";
		if (mCars != null && !mCars.isEmpty()) {
			Iterator<Entry<String, DriverCar>> it = mCars.entrySet().iterator();
			while(it.hasNext()) {
				cars += "{ " + it.next().getValue().serializeJSON() + " },";
			}
			cars = cars.substring(0, cars.length() - 2);
		}
		cars += "]";

		try {
			FileManager.writeFile(cars, c.openFileOutput(Constants.FILE_USER_CARS, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void initUser(String userId, String apiToken) {
		mUser = new User();
		mUser.setApiToken(apiToken);
		mUser.setUserId(userId);
	}
}
