package order_management;

import java.util.ArrayList;

public class CdManagementImpl implements CrdManagementInterface{

	private ArrayList<Crd> crds = new ArrayList<Crd>();
	
	@Override
	public boolean createCrd(Rep rep) {
		crds.add(new Crd(rep));
		return true;
	}

	@Override
	public Crd getCrd(Rep rep) {
		for(Crd crd : crds){
			if (crd.getIdentifier() == rep.getIdentifier()){
				return crd;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Crd> getAllCrd() {
		return crds;
	}
	
	

}
