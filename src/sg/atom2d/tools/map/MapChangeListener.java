package sg.atom2d.tools.map;

interface MapChangeListener {
	public void mapChanging(boolean major);
	public void mapChanged(boolean major);
}
