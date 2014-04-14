<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:param name="paramSample"/>
	
	<xsl:template match="/">
		<customerCode>
			<xsl:value-of select="*/customerCode" />
		</customerCode>
		<lalala>
			<xsl:value-of select="$paramSample" />
		</lalala>
	</xsl:template>

</xsl:stylesheet>