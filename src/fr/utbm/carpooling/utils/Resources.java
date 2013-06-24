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
		return toArrayList(mColors);
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

	}
}
