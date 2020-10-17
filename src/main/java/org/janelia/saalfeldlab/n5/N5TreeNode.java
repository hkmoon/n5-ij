/**
 * License: GPL
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.janelia.saalfeldlab.n5;

import org.janelia.saalfeldlab.n5.metadata.N5Metadata;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class N5TreeNode extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = -6433341489220400345L;

	public final String path;

	private boolean isDataset;

	private N5Metadata metadata;

	public N5TreeNode( final String path, final boolean isDataset )
	{
		super();
		this.path = path;
		this.isDataset = isDataset;
	}

    public String getNodeName()
    {
        return Paths.get(removeLeadingSlash(path)).getFileName().toString();
    }

	@SuppressWarnings( "unchecked" )
	public List< N5TreeNode > childrenList()
	{
		return Collections.list( children() );
	}

	public void setIsDataset( final boolean isDataset )
	{
		this.isDataset = isDataset;
	}

	public boolean isDataset()
	{
		return isDataset;
	}

	public void setMetadata( final N5Metadata metadata )
	{
		this.metadata = metadata;
	}

	public N5Metadata getMetadata()
	{
		return metadata;
	}

    @Override
    public String toString()
    {
        final String nodeName = getNodeName();
        return !nodeName.isEmpty() ? nodeName : "/";
    }

    /**
     * Removes the leading slash from a given path and returns the corrected path.
     * It ensures correctness on both Unix and Windows, otherwise {@code pathName} is treated
     * as UNC path on Windows, and {@code Paths.get(pathName, ...)} fails with {@code InvalidPathException}.
     *
     * @param pathName
     * @return
     */
    protected static String removeLeadingSlash(final String pathName) {

        return pathName.startsWith("/") || pathName.startsWith("\\") ? pathName.substring(1) : pathName;
    }
}