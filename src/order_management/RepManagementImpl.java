package order_management;

import java.util.ArrayList;

public class RepManagementImpl implements RepManagementInterface {
	
	private ArrayList<Rep> reps = new ArrayList<Rep>();
	private ArrayList<Rep> historyReps = new ArrayList<Rep>();
	
	@Override
	public Rep createRep(String uname, String clientName) {
		return new Rep(uname, clientName);
	}


	@Override
	public boolean addRep(Rep rep) {		
		if(rep != null)
			reps.add(rep);		
		return true;
	}

	@Override
	public ArrayList<Rep> listRep(String uname) {
		
		if(uname.equals("all")){
			return reps;
		}else{
			ArrayList<Rep> list = new ArrayList<Rep>();
			for(Rep rep: reps){
				if(rep.getUname().equals(uname)){
					list.add(rep);
				}
			}
			return list;
		}
	}


	@Override
	public Rep getRep(int id) {
		for(Rep rep: reps){
			if(rep.getIdentifier()==id){
				return rep;
			}
		}
		return null;
	}


	@Override
	public void deleteRep(int id) {		
		for(Rep rep: reps){
			if(rep.getIdentifier()==id){
				historyReps.add(rep);
				reps.remove(rep);
			}
		}
	}


	@Override
	public ArrayList<Rep> getRep(RepState state) {
		ArrayList<Rep> newRep = new ArrayList<Rep>();
		for(Rep rep: reps){
			if(rep.getState()==state){
				newRep.add(rep);
			}				
		}
		return newRep;
	}


}
