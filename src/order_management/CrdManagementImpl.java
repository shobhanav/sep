package order_management;

import java.util.ArrayList;
import java.util.Iterator;

public class CrdManagementImpl implements CrdManagementInterface{

	private ArrayList<Crd> crds = new ArrayList<Crd>();
	private ArrayList<Crd> historyCrds = new ArrayList<Crd>();
	
	@Override
	public Crd createCrd(Rep rep) {
		if (rep.getState() != RepState.APPROVED){
			return null; //avoid the creation of the crd for a wrong rep
		}
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
		for(Iterator<Crd> itCrd = crds.iterator(); itCrd.hasNext();){
			Crd crd = itCrd.next();
			if(crd.getIdentifier()==id){
				historyCrds.add(crd);
				itCrd.remove();
			}
		}
	}

	@Override
	public Crd getCrd(int id) {
		for(Crd crd : crds){
			if (crd.getIdentifier() == id){
				return crd;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Crd> getCrdHistory() {
		return historyCrds;
	}

}
