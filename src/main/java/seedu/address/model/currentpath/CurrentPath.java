package seedu.address.model.currentpath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.tag.FileAddress;

import static java.util.Objects.requireNonNull;

/**
 * CurrentPath contains the current path (a FileAddress) of the HelloFile
 * file explorer. It also contains the list of children files under the path.
 */
public class CurrentPath {

    private FileAddress address;
    private FileList childrenFiles;

    /**
     * Constructs a CurrentPath.
     * @param fileList the list to be the children file list
     */
    public CurrentPath(FileList fileList) {
        requireNonNull(fileList);
        address = new FileAddress(System.getProperty("user.dir"));
        childrenFiles = fileList;
        updateChildrenFileList();
    }

    public FileAddress getAddress() {
        return address;
    }

    public String getParentAddress() {
        File file = new File(address.value);
        return file.getParent();
    }

    public void setAddress(FileAddress address) {
        requireNonNull(address);
        this.address = address;
        updateChildrenFileList();
    }

    private void updateChildrenFileList() {
        File folder = new File(address.value);
        List<File> files = new ArrayList<>();

        for (File file : folder.listFiles()) {
            files.add(file);
        }

        childrenFiles.setFiles(files);
    }
}
