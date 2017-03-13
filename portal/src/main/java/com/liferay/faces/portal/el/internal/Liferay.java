/**
 * Copyright (c) 2000-2017 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.faces.portal.el.internal;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

import com.liferay.faces.portal.context.LiferayPortletHelperUtil;
import com.liferay.faces.portal.el.internal.PortraitURLMap;
import com.liferay.faces.portal.el.internal.ThemeImageURLMap;
import com.liferay.faces.portal.el.internal.UserPermissionMap;
import com.liferay.faces.util.helper.BooleanHelper;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;


/**
 * @author  Neil Griffin
 */
@ManagedBean(name = "liferay")
@ViewScoped
public class Liferay implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 2084947229350893151L;

	// Private Data Members: All marked as transient since they are lazy-initialized.
	private transient Long companyId;
	private transient String documentLibraryURL;
	private transient String imageURL;
	private transient User groupUser;
	private transient String imageGalleryURL;
	private transient Boolean inlineInputEditor;
	private transient Layout layout;
	private transient String portalURL;
	private transient Portlet portlet;
	private transient PortraitURLMap portraitURLMap;
	private transient ThemeImageURLMap themeImageURLMap;
	private transient Theme theme;
	private transient ThemeDisplay themeDisplay;
	private transient String themeImagesURL;
	private transient User user;
	private transient UserPermissionMap userPermissionMap;

	public Liferay() {

		// FACES-1212: Cache the "RENDER_PORTLET" request attribute here in the constructor since it is only
		// available during the RENDER_PHASE of the portlet lifecycle.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PortletRequest portletRequest = (PortletRequest) facesContext.getExternalContext().getRequest();
		portlet = (Portlet) portletRequest.getAttribute(WebKeys.RENDER_PORTLET);
	}

	public int getBuildNumber() {
		return LiferayPortletHelperUtil.getBuildNumber();
	}

	/**
	 * Returns the Liferay companyId primary key value associated with the community/organization portal page that the
	 * current portlet is placed upon.
	 */
	public Long getCompanyId() {

		if (companyId == null) {
			companyId = LiferayPortletHelperUtil.getCompanyId();
		}

		return companyId;
	}

	/**
	 * Returns the absolute URL for the Liferay Document Library Struts action path. For example:
	 * http://localhost:8080/c/document_library
	 */
	public String getDocumentLibraryURL() {

		if (documentLibraryURL == null) {
			documentLibraryURL = LiferayPortletHelperUtil.getDocumentLibraryURL();
		}

		return documentLibraryURL;
	}

	/**
	 * Returns the absolute URL for the Liferay Document Library Struts action path. For example:
	 * http://localhost:8080/image/image_gallery
	 */
	public String getImageGalleryURL() {

		if (imageGalleryURL == null) {
			imageGalleryURL = LiferayPortletHelperUtil.getImageGalleryURL();
		}

		return imageGalleryURL;
	}

	/**
	 * Returns the absolute URL for the Liferay Image Servlet. Although this can be used to construct a URL that points
	 * a Liferay user's portrait/photo, for performance reasons, it is better to call {@link #getPortraitURL()} instead.
	 */
	public String getImageURL() {

		if (imageURL == null) {
			imageURL = LiferayPortletHelperUtil.getImageGalleryURL();
		}

		return imageURL;
	}

	/**
	 * Returns the Liferay {@link Layout} associated with the community/organization portal page that the containing
	 * portlet is placed upon.
	 */
	public Layout getLayout() {

		if (layout == null) {
			layout = LiferayPortletHelperUtil.getLayout();
		}

		return layout;
	}

	/**
	 * Returns the absolute URL for the portal. For example: http://localhost:8080
	 */
	public String getPortalURL() {

		if (portalURL == null) {
			portalURL = LiferayPortletHelperUtil.getPortalURL();
		}

		return portalURL;
	}

	/**
	 * Returns the containing Liferay {@link Portlet} associated with the {@link PortletRequest}.
	 */
	public Portlet getPortlet() {
		return portlet;
	}

	/**
	 * Designed to be called from the EL by passing a Liferay {@link User} or <code>userId</code> as an array index,
	 * returns the absolute URL to the user's portrait. The actual return value is of type {@link PortraitURLMap} which
	 * provides a means of caching Liferay portrait image URLs in a HashMap for convenient and fast lookup.
	 */
	public PortraitURLMap getPortraitURL() {

		if (portraitURLMap == null) {
			String liferayImageURL = getPortalURL() + "/image";
			portraitURLMap = new PortraitURLMap(liferayImageURL);
		}

		return portraitURLMap;
	}

	/**
	 * Returns the Liferay {@link User} that owns the Liferay community/organization portal page that the containing
	 * portlet is placed upon.
	 */
	public User getScopeGroupUser() {

		if (groupUser == null) {
			groupUser = LiferayPortletHelperUtil.getScopeGroupUser();
		}

		return groupUser;
	}

	/**
	 * Returns the Liferay {@link Theme} associated with the Liferay {@link Layout}.
	 */
	public Theme getTheme() {

		if (theme == null) {
			theme = LiferayPortletHelperUtil.getTheme();
		}

		return theme;
	}

	/**
	 * Returns the Liferay {@link ThemeDisplay} associated with the {@link PortletRequest}.
	 */
	public ThemeDisplay getThemeDisplay() {

		if (themeDisplay == null) {
			themeDisplay = LiferayPortletHelperUtil.getThemeDisplay();
		}

		return themeDisplay;
	}

	/**
	 * Returns the absolute URL for the image path associated with the current Liferay Theme. For example:
	 * http://localhost:8080/image/image_gallery
	 */
	public String getThemeImagesURL() {

		if (themeImagesURL == null) {
			themeImagesURL = LiferayPortletHelperUtil.getThemeImagesURL();
		}

		return themeImagesURL;
	}

	/**
	 * Designed to be called from the EL by passing a relative path to a theme image as an array index, returns the
	 * absolute URL to the theme image. The actual return value is of type {@link ThemeImageURLMap} which provides a
	 * means of caching Liferay theme image URLs in a HashMap for convenient and fast lookup.
	 */
	public ThemeImageURLMap getThemeImageURL() {

		if (themeImageURLMap == null) {
			themeImageURLMap = new ThemeImageURLMap(getThemeImagesURL());
		}

		return themeImageURLMap;
	}

	/**
	 * Returns the Liferay {@link User} associated with the {@link PortletRequest}.
	 */
	public User getUser() {

		if (user == null) {
			user = LiferayPortletHelperUtil.getUser();
		}

		return user;
	}

	/**
	 * Designed to be called from the EL by passing an action-key as an array index, returns a Boolean indicating
	 * whether or not the Liferay {@link User} associated with the {@link PortletRequest} has permission to execute the
	 * specified action-key on the current portlet.
	 */
	public UserPermissionMap getUserHasPortletPermission() {

		if (userPermissionMap == null) {
			userPermissionMap = new UserPermissionMap();
		}

		return userPermissionMap;
	}

	public boolean isInlineInputEditor() {

		if (inlineInputEditor == null) {
			inlineInputEditor = Boolean.FALSE;

			int buildNumber = LiferayPortletHelperUtil.getBuildNumber();

			if (buildNumber >= 6012) {
				inlineInputEditor = Boolean.TRUE;
			}
			else {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ExternalContext externalContext = facesContext.getExternalContext();
				String configOption = externalContext.getInitParameter("com.liferay.faces.portal.inlineInputEditor");

				if (configOption == null) {

					// Backwards compatibility
					configOption = externalContext.getInitParameter("org.portletfaces.liferay.faces.inlineInputEditor");
				}

				if ((configOption != null) && BooleanHelper.isTrueToken(configOption)) {
					inlineInputEditor = Boolean.TRUE;
				}
			}
		}

		return inlineInputEditor;
	}
}
