/*
 * Sleuth Kit Data Model
 *
 * Copyright 2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.datamodel.timeline.filters;

import java.util.Objects;
import org.sleuthkit.datamodel.timeline.DescriptionLoD;

public class DescriptionFilter extends AbstractFilter {

	private final DescriptionLoD descriptionLoD;
	private final String description;
	private final FilterMode filterMode;

	public DescriptionFilter(DescriptionLoD descriptionLoD, String description, FilterMode filterMode) {
		this.descriptionLoD = descriptionLoD;
		this.description = description;
		this.filterMode = filterMode;
	}

	public FilterMode getFilterMode() {
		return filterMode;
	}

	@Override
	public DescriptionFilter copyOf() {
		DescriptionFilter filterCopy = new DescriptionFilter(getDescriptionLoD(), getDescription(), getFilterMode());
		filterCopy.setSelected(isSelected());
		filterCopy.setDisabled(isDisabled());
		return filterCopy;
	}

	@Override
	public String getDisplayName() {
		return getDescriptionLoD().getDisplayName() + ": " + getDescription();
	}

	/**
	 * @return the descriptionLoD
	 */
	public DescriptionLoD getDescriptionLoD() {
		return descriptionLoD;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public enum FilterMode {

		EXCLUDE(BundleUtils.getBundle().getString("DescriptionFilter.mode.exclude")),
		INCLUDE(BundleUtils.getBundle().getString("DescriptionFilter.mode.include"));

		private final String displayName;

		private FilterMode(String displayName) {
			this.displayName = displayName;
		}

		private String getDisplayName() {
			return displayName;
		}
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.descriptionLoD);
		hash = 79 * hash + Objects.hashCode(this.description);
		hash = 79 * hash + Objects.hashCode(this.filterMode);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DescriptionFilter other = (DescriptionFilter) obj;
		if (this.descriptionLoD != other.descriptionLoD) {
			return false;
		}
		if (!Objects.equals(this.description, other.description)) {
			return false;
		}
		return this.filterMode == other.filterMode;
	}
}