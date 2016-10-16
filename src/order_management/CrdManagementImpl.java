package order_management;

import java.util.ArrayList;

public class CrdManagementImpl implements CrdManagementInterface{

	private ArrayList<Crd> crds = new ArrayList<Crd>();
	private ArrayList<Crd> historyCrds = new ArrayList<Crd>();
	
	@Override
	public Crd createCrd(Rep rep) {
		Crd newCrd = new Crd(rep);
		crds.add(newCrd);
		return newCrd;
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

	@Override
	public void deleteCrd(int id) {
		for(Crd crd: crds){
			if(crd.getIdentifier()==id){
				historyCrds.add(crd);
				crds.remove(crd);
			}
		}
	}

}
