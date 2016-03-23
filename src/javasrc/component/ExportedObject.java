package javasrc.component;

import java.util.List;

public interface ExportedObject {
	public List<List<String>> getData();
	public int getStartrownum();
	public boolean haveNext();
}
