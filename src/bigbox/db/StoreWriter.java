package bigbox.db;

import bigbox.business.Store;

public interface StoreWriter {
	void addStore(Store storeIN);

	void updateStore(Store storeIN);

	void deleteStore(Store storeIN);


}
