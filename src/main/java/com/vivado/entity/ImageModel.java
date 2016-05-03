/**
 * 
 */
package com.vivado.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 虚拟机模板
 * @author 德龙
 *
 */
public class ImageModel implements Serializable {
	
	private static final long serialVersionUID = 6815076464490812208L;

	/**模板名称*/
    private String imageName;

    /**模板路径*/
    private String imagePath;

    /**模板大小*/
    private Long imageSize;

    /**模板描述*/
    private String imageDesc;

    /**模板状态*/
    private String imageState;

    /**模板id*/
    private String imageId;

    private String imageUpdated;

    private String imageMeta;

    private String imageType;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getImageSize() {
		return imageSize;
	}

	public void setImageSize(Long imageSize) {
		this.imageSize = imageSize;
	}

	public String getImageDesc() {
		return imageDesc;
	}

	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}

	public String getImageState() {
		return imageState;
	}

	public void setImageState(String imageState) {
		this.imageState = imageState;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageUpdated() {
		return imageUpdated;
	}

	public void setImageUpdated(String imageUpdated) {
		this.imageUpdated = imageUpdated;
	}

	public String getImageMeta() {
		return imageMeta;
	}

	public void setImageMeta(String imageMeta) {
		this.imageMeta = imageMeta;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
    
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
