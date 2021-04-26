package common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardFileRenamePolicy implements com.oreilly.servlet.multipart.FileRenamePolicy {

	@Override
	public File rename(File f) {
		File newFile = null;
		do {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat df = new DecimalFormat("000");
			String oldName = f.getName();
			String ext = "";
			int dot = oldName.lastIndexOf(".");
			if(dot > -1) ext = oldName.substring(dot);
			String newName = sdf.format(new Date())
							+ df.format(Math.random() * 999)
							+ ext;
			newFile = new File(f.getParent(), newName);
		} while(!createNewFile(newFile));
		return newFile;
	}
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}
}
