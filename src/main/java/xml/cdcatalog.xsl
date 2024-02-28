<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
    <body>
    <h2>My CD collection</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Title</th>
        <th>Artist</th>
      </tr>
      <xsl:for-each select="catalog/cd">

        <tr>
          <!-- selector path begins with catalog/-->
          <td><xsl:value-of select="title"/></td> 
          <td><xsl:value-of select="artist"/></td>
        </tr>
      </xsl:for-each>

    </table>
    </body>
  </html>
</xsl:template>

</xsl:stylesheet>
