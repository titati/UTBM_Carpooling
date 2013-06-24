package fr.utbm.carpooling.utils;


import fr.utbm.carpooling.model.*;
import fr.utbm.carpooling.model.wrapper.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

public class Resources {

	private static SparseArray<Color> mColors;
	private static SparseArray<Trunk> mTrunks;
	private static SparseArray<Site> mSites;
	private static SparseArray<Brand> mBrands;
	private static SparseArray<SparseArray<Model>> mModels;
	private static ArrayList<Color> mColorsSorted;
	private static User mUser = null;
	private static SparseArray<DriverCar> mCars;

	public static void initUser(String userId, String apiToken) {
		mUser = new User();
		mUser.setApiToken(apiToken);
		mUser.setUserId(userId);
		mCars = new SparseArray<DriverCar>();
	}

	public static void setUserInfos(UserInfos object, Context context) {
		setUser(object.getUser());
		setCars(object.getCars());
		saveUser(context);
		saveCars(context);
	}

	public static void setUser(UserShort object) {
		mUser.setEmail(object.getEmail());
		mUser.setPhone(object.getPhone());
		mUser.setFirstname(object.getFirstname());
		mUser.setLastname(object.getLastname());
		mUser.setUserId(object.getUserId());
	}

	public static void saveUser(Context context) {
		try {
			FileManager.writeFile("{ " + mUser.serializeJSON() + " }", context.openFileOutput(Constants.FILE_USER_INFO, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static User getUser() {
		return mUser;
	}

	public static void resetUser() {
		mUser = null;
		mCars = null;
	}

	public static void setCars(ArrayList<DriverCar> list) {
		if (list != null && !list.isEmpty()) {
			for(DriverCar c : list) {
				mCars.put(c.getId(), c);
			}
		}
	}

	public static void saveCars(Context context) {
		String str = "[";
		
		if (mCars != null) {
			for(int i = 0; i < mCars.size(); ++i) {
				str += "{ " + mCars.valueAt(i).serializeJSON() + " },";
			}
			str = str.substring(0, str.length() - 1);
		}
		
		str += "]";
		
		try {
			FileManager.writeFile(str, context.openFileOutput(Constants.FILE_USER_CARS, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<DriverCar> getCars() {
		return toArrayList(mCars);
	}

	public static void deleteCar(int id) {
		boolean wasDefault = false;
		
		if (mCars.get(id).isDefaultCar() && mCars.size() > 1) {
			wasDefault = true;
		}
		
		mCars.remove(id);
		
		if (wasDefault) {
			mCars.valueAt(0).setDefaultCar(true);
		}
	}
	
	public static void setColors(ArrayList<Color> list) {
		mColors = new SparseArray<Color>();
		
		if (list != null && !list.isEmpty()) {
			for(Color c : list) {
				mColors.put(c.getId(), c);
			}
		}
	}
	
	public static void saveColors(Context context) {
		String str = "[";
		
		if (mColors != null) {
			for(int i = 0; i < mColors.size(); ++i) {
				str += "{ " + mColors.valueAt(i).serializeJSON() + " },";
			}
			str = str.substring(0, str.length() - 1);
		}
		
		str += "]";
		
		try {
			FileManager.writeFile(str, context.openFileOutput(Constants.FILE_COLORS, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Color> getColors() {
		return mColorsSorted;
	}

	public static Color getColor(int id) {
		return mColors.get(id);
	}

	public static ArrayList<Trunk> getTrunks() {
		return toArrayList(mTrunks);
	}
	
	public static void setTrunks(SparseArray<Trunk> trunks) {
		mTrunks = trunks;
	}

	public static Trunk getTrunk(int id) {
		return mTrunks.get(id);
	}
	
	public static void setSites(ArrayList<Site> list) {
		mSites = new SparseArray<Site>();
		
		if (list != null && !list.isEmpty()) {
			for(Site c : list) {
				mSites.put(c.getId(), c);
			}
		}
	}
	
	public static void saveSites(Context context) {
		String str = "[";
		
		if (mSites != null) {
			for(int i = 0; i < mSites.size(); ++i) {
				str += "{ " + mSites.valueAt(i).serializeJSON() + " },";
			}
			str = str.substring(0, str.length() - 1);
		}
		
		str += "]";
		
		try {
			FileManager.writeFile(str, context.openFileOutput(Constants.FILE_SITES, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Site> getSites() {
		return toArrayList(mSites);
	}

	public static ArrayList<SiteShort> getSitesShort() {
		ArrayList<SiteShort> siteShort = new ArrayList<SiteShort>();
		
		for(int i = 0; i < mSites.size(); ++i) {
			siteShort.add(new SiteShort(mSites.keyAt(i), mSites.valueAt(i).getName()));
		}
		
		return siteShort;
	}

	public static Site getSite(int id) {
		return mSites.get(id);
	}

	public static void setBrands(ArrayList<Brand> list) {
		mBrands = new SparseArray<Brand>();
		mModels = new SparseArray<SparseArray<Model>>();
		
		if (list != null && !list.isEmpty()) {
			for(Brand b : list) {
				mBrands.put(b.getId(), b);
				mModels.put(b.getId(), new SparseArray<Model>());
			}
		}
	}
	
	public static void saveBrands(Context context) {
		String str = "[";
		
		if (mBrands != null) {
			for(int i = 0; i < mBrands.size(); ++i) {
				str += "{ " + mBrands.valueAt(i).serializeJSON() + " },";
			}
			str = str.substring(0, str.length() - 1);
		}
		
		str += "]";
		
		Log.i("saveBrands", str + "\nTaille : " + mBrands.size());
		
		try {
			FileManager.writeFile(str, context.openFileOutput(Constants.FILE_BRANDS, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Brand> getBrands() {
		return toArrayList(mBrands);
	}

	public static Brand getBrand(int id) {
		return mBrands.get(id);
	}
	
	public static void setModels(ArrayList<Model> list) {
		if (list != null && !list.isEmpty()) {
			for(Model b : list) {
				mModels.get(b.getBrandId()).put(b.getId(), b);
			}
		}
	}
	
	public static void saveModels(Context context) {
		String str = "[";
		
		if (mModels != null) {
			for(int i = 0; i < mModels.size(); ++i) {
				for(int j = 0; j < mModels.valueAt(i).size(); ++j) {
					str += "{ " + mModels.valueAt(i).valueAt(j).serializeJSON() + " },";
				}
			}
			str = str.substring(0, str.length() - 1);
		}
		
		str += "]";
		
		try {
			FileManager.writeFile(str, context.openFileOutput(Constants.FILE_MODELS, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Model> getModels(int brandId) {
		return toArrayList(mModels.get(brandId));
	}

	public static Model getModel(int brandId, int id) {
		return mModels.get(brandId).get(id);
	}
	
	private static <E> ArrayList<E> toArrayList(SparseArray<E> array) {
		ArrayList<E> list = new ArrayList<E>();
		
		if (array != null) for(int i = 0; i < array.size(); ++i) {
			list.add(array.valueAt(i));
		}
		
		return list;
	}

	public static void init() {
//		mColors.put(0, new Color(0, "000000"));
//		mColors.put(1, new Color(1, "808080"));
//		mColors.put(2, new Color(2, "FFFFFF"));
//		mColors.put(3, new Color(3, "000080"));
//		mColors.put(4, new Color(4, "0000FF"));
//		mColors.put(5, new Color(5, "008000"));
//		mColors.put(6, new Color(6, "00FF00"));
//		mColors.put(7, new Color(7, "800000"));
//		mColors.put(8, new Color(8, "FF0000"));
//		mColors.put(9, new Color(9, "FFFF00"));
//		mColors.put(10, new Color(10, "00FFFF"));
//		mColors.put(11, new Color(11, "FF00FF"));
//
//		mColorsSorted = new ArrayList<Color>();
//		mColorsSorted.addAll(toArrayList(mColors));
//		Collections.sort(mColorsSorted);
//
//		mSites.put(0, new Site(0, "belfort"));
//		mSites.put(1, new Site(1, "sévenans"));
//		mSites.put(2, new Site(2, "montbéliard"));
//		mSites.put(3, new Site(3, "sbarro"));
//
//		mBrands.put(0, new Brand(0, "renault"));
//		mBrands.put(1, new Brand(1, "peugeot"));
//		mBrands.put(2, new Brand(2, "citroën"));
//
//
//		SparseArray<Model> renault_models = new SparseArray<Model>();
//
//		renault_models.put(0, new Model(0, 0, "clio"));
//		renault_models.put(1, new Model(0, 1, "twingo"));
//		renault_models.put(2, new Model(0, 2, "mégane"));
//		renault_models.put(3, new Model(0, 3, "espace"));
//
//		SparseArray<Model> peugeot_models = new SparseArray<Model>();
//
//		peugeot_models.put(0, new Model(1, 0, "206"));
//		peugeot_models.put(1, new Model(1, 1, "306"));
//		peugeot_models.put(2, new Model(1, 2, "406"));
//		peugeot_models.put(3, new Model(1, 3, "307"));
//
//		SparseArray<Model> citroen_models = new SparseArray<Model>();
//
//		citroen_models.put(0, new Model(2, 0, "C1"));
//		citroen_models.put(1, new Model(2, 1, "C2"));
//		citroen_models.put(2, new Model(2, 2, "C3"));
//		citroen_models.put(3, new Model(2, 3, "C4"));
//
//
//		mModels.put(0, renault_models);
//		mModels.put(1, peugeot_models);
//		mModels.put(2, citroen_models);
	}
}
