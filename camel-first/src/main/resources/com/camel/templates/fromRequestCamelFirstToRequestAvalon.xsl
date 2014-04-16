<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template match="/">

		<customerCode>
			<!-- xpath tutorial: "cliente" -> Selects all nodes with the name "cliente" -->
			<xsl:value-of select="cliente" />
		</customerCode>

	</xsl:template>

</xsl:stylesheet>