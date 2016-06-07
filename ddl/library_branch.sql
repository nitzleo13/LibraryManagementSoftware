USE [Bodhi_lbms]
GO

/****** Object:  Table [dbo].[library_branch]    Script Date: 11/3/2014 7:18:56 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[library_branch](
	[branch_id] [varchar](50) NOT NULL,
	[branch_name] [varchar](100) NOT NULL,
	[address] [varchar](250) NOT NULL,
 CONSTRAINT [PK_library_branch] PRIMARY KEY CLUSTERED 
(
	[branch_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


